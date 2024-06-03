<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';
	import { writable } from 'svelte/store';
	import { goto } from '$app/navigation';
	import SeatGrid from '$lib/components/SeatGrid.svelte';

	let reservations = [];
	let selectedReservation = null;
	let showEditForm = writable(false);
	let sortBy = 'startTime';
	let seats = [];
	let selectedSeats = writable([]);
	let allSeats = [];

	const loadReservations = async () => {
		try {
			const token = Cookies.get('token');
			const user = await parseJwt(token);

			const response = await axiosInstance.get('/reservations?username=' + user.sub);

			reservations = response.data;
			console.log('Reservations loaded:', reservations);
		} catch (error) {
			console.error('Error loading reservations:', error);
		}
	};

	const loadSeats = async (roomId) => {
		try {
			const response = await axiosInstance.get('/seats', {
				params: { roomId }
			});
			allSeats = response.data;
			selectedSeats.set([]);
			selectedSeats.set(selectedReservation.seats.map(seat => seat.id));
			seats = allSeats;
		} catch (error) {
			console.error('Error loading seats:', error);
		}
	};

	const handleEdit = async (reservation) => {
		selectedReservation = reservation;
		await loadSeats(reservation.projection.room.id);
		selectedSeats.set(reservation.seats.map(seat => seat.id));
		showEditForm.set(true);
	};

	const handleSave = async () => {
		try {
			const token = Cookies.get('token');
			const user = await parseJwt(token);
			const updatedReservation = {
				username: user.sub,
				projectionId: selectedReservation.projection.id,
				paid: selectedReservation.paid,
				discount: selectedReservation.discount,
				seats: $selectedSeats
			};
			console.log("Zvolené sedačky (seatsId):", $selectedSeats);
			console.log("Aktualizovaná rezervace:", updatedReservation);
			const response = await axiosInstance.put(`/reservations/${selectedReservation.id}`, updatedReservation);
			console.log("Odpověď z backendu:", response.data);
			showEditForm.set(false);
			loadReservations();
		} catch (error) {
			console.error('Error saving reservation details:', error);
		}
	};

	const handleDelete = async (reservationId) => {
		try {
			const response = await axiosInstance.delete(`/reservations/${reservationId}`);
			console.log(response);
			loadReservations();
		} catch (error) {
			console.error('Error deleting reservation:', error);
		}
	};

	const changeSort = (newSortBy) => {
		if (sortBy === newSortBy) return;
		sortBy = newSortBy;
		loadReservations();
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
		<table class="table">
			<thead>
			<tr>
				<th on:click={() => changeSort('movie.title')}>Film {sortBy === 'movie.title' ? '↓' : ''}</th>
				<th on:click={() => changeSort('startTime')}>Čas {sortBy === 'startTime' ? '↓' : ''}</th>
				<th on:click={() => changeSort('paid')}>Zaplaceno {sortBy === 'paid' ? '↓' : ''}</th>
				<th on:click={() => changeSort('discount')}>Sleva {sortBy === 'discount' ? '↓' : ''}</th>
				<th>Sedadla</th>
				<th>Akce</th>
			</tr>
			</thead>
			<tbody>
			{#each reservations as reservation}
				<tr>
					<td>{reservation.projection.movie ? reservation.projection.movie.title : 'Neznámý film'}</td>
					<td>{new Date(reservation.projection.startTime).toLocaleString()}</td>
					<td>{reservation.paid ? 'Ano' : 'Ne'}</td>
					<td>{reservation.discount}%</td>
					<td>
						{#each reservation.seats as seat, index (seat.id)}
							<span>{seat.roomRow}{seat.seatNumber}{index < reservation.seats.length - 1 ? ', ' : ''}</span>
						{/each}
					</td>
					<td>
						<button class="btn btn-secondary" on:click={() => handleEdit(reservation)}>Upravit</button>
						<button class="btn btn-danger" on:click={() => handleDelete(reservation.id)}>Smazat</button>
					</td>
				</tr>
			{/each}
			</tbody>
		</table>
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
				<div class="form-group">
					<label for="seats">Sedadla:</label>
					<SeatGrid { seats } bind:selectedSeats={$selectedSeats} />
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