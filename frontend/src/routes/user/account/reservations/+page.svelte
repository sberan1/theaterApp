<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';
	import { writable } from 'svelte/store';
	import { goto } from '$app/navigation';
	import ProjectionTable from '$lib/components/ProjectionTable.svelte';

	let reservations = [];
	let selectedReservation = null;
	let showEditForm = writable(false);

	const loadReservations = async () => {
		try {
			const token = Cookies.get('token');
			const user = await parseJwt(token);

			const response = await axiosInstance.get('/reservations', {
				params: { username: user.sub },
				headers: { 'Authorization': `Bearer ${token}` }
			});

			reservations = response.data;
			console.log('Reservations loaded:', reservations);
		} catch (error) {
			console.error('Error loading reservations:', error);
		}
	};

	const handleEdit = (reservation) => {
		selectedReservation = reservation;
		showEditForm.set(true);
	};

	const handleSave = async () => {
		try {
			const token = Cookies.get('token');
			await axiosInstance.put(`/reservations/${selectedReservation.id}`, selectedReservation, {
				headers: { 'Authorization': `Bearer ${token}` }
			});
			showEditForm.set(false);
			loadReservations();
		} catch (error) {
			console.error('Error saving reservation details:', error);
		}
	};

	async function parseJwt(token) {
		const base64Url = token.split('.')[1];
		const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
		const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
			return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
		}).join(''));

		return JSON.parse(jsonPayload);
	}

	onMount(loadReservations);
</script>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<button class="btn btn-primary mr-2" on:click={() => goto('/user/account')}>Vrať se zpět</button>
		</div>
	</nav>
</header>

<div class="container mt-4">
	<h3>Moje rezervace</h3>
	{#if reservations.length === 0}
		<p>Nemáte žádné rezervace.</p>
	{:else}
		<ProjectionTable
			projections={reservations.map(reservation => ({
        ...reservation.projection,
        id: reservation.id,
        paid: reservation.paid,
        discount: reservation.discount
      }))}
			handleEdit={handleEdit}
			handleDelete={handleDelete}
			changeSort={changeSort}
			sortBy={sortBy}
		/>
	{/if}
	{#if $showEditForm}
		<div class="edit-form mt-4">
			<h3>Upravit rezervaci</h3>
			<form on:submit|preventDefault={handleSave}>
				<div class="form-group">
					<label for="projection">Projekce:</label>
					<select id="projection" class="form-control" bind:value={selectedReservation.projectionId}>
						{#each reservations as reservation}
							<option value={reservation.id}>
								{reservation.projection.movie.title} - {new Date(reservation.projection.startTime).toLocaleString()}
							</option>
						{/each}
					</select>
				</div>
				<div class="form-group">
					<label for="isPaid">Zaplaceno:</label>
					<input id="isPaid" type="checkbox" bind:checked={selectedReservation.paid} />
				</div>
				<div class="form-group">
					<label for="discount">Sleva:</label>
					<input id="discount" type="number" min="0" max="100" bind:value={selectedReservation.discount}
								 class="form-control" />
				</div>
				<button type="submit" class="btn btn-success">Uložit</button>
			</form>
		</div>
	{/if}
</div>

<style>
    .container {
        max-width: 800px;
        margin: auto;
    }

    .mt-4 {
        margin-top: 1.5rem;
    }

    .edit-form {
        margin-top: 1.5rem;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 4px;
        background-color: #f9f9f9;
    }
</style>