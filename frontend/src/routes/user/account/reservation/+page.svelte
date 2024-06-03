<script>
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';
	import ProjectionTable from '$lib/components/ProjectionTable.svelte';
	import SeatGrid from '$lib/components/SeatGrid.svelte';
	import { writable } from 'svelte/store';

	let projections = [];
	let selectedProjection;
	let isPaid = false;
	let discount = 0;
	let selectedSeats = writable([]);
	let seats = [];

	let filterType = 'movie';
	let filterValue = '';
	let filteredProjections = writable([]);
	let sortBy = 'startTime';

	const loadProjections = async () => {
		try {
			const response = await axiosInstance.get('/projections');
			projections = response.data;
			console.log('Projections loaded:', projections);
			applyFilter();
		} catch (error) {
			console.error('Error loading projections:', error);
		}
	};

	const applyFilter = () => {
		let result = projections;
		if (filterValue) {
			result = projections.filter(projection => {
				if (filterType === 'movie') {
					return projection.movie && projection.movie.title.includes(filterValue);
				} else if (filterType === 'branch') {
					return projection.room && projection.room.branch && projection.room.branch.name.includes(filterValue);
				}
			});
		}
		filteredProjections.set(result);
	};

	const changeSort = (newSortBy) => {
		if (sortBy === newSortBy) return;
		sortBy = newSortBy;
		applyFilter();
	};

	const loadSeats = async () => {
		try {
			if (selectedProjection) {
				const rep = await axiosInstance.get(`/user/projection/${selectedProjection}`);
				console.log(rep);
				const response = await axiosInstance.get('/seats', {
					params: { roomId: rep.data.room.id }
				});
				seats = response.data;
			}
		} catch (error) {
			console.error('Error loading seats:', error);
		}
	};

	const createReservation = async () => {
		try {
			const token = Cookies.get('token');
			const user = await parseJwt(token);

			const reservationData = {
				username: user.sub,
				projectionId: selectedProjection,
				paid: isPaid,
				discount,
				seats: $selectedSeats.map(seat => seat.id)
			};
			console.log(reservationData);

			const response = await axiosInstance.post('/reservation', reservationData);
			console.log('Reservation created:', response.data);
			goto('/user/account');
		} catch (error) {
			if (error.response && error.response.status === 409) {
				alert('Projekce byla aktualizována jinou transakcí. Prosím, obnovte stránku a zkuste to znovu.');
			} else {
				console.error('Error creating reservation:', error);
			}
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

	onMount(loadProjections);
</script>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<button class="btn btn-primary mr-2" on:click={() => goto('/user/account')}>Vrať se zpět</button>
		</div>
	</nav>
</header>

<div class="container mt-4">
	<h3>Vytvořit rezervaci</h3>
	<div class="form-group">
		<label for="filterType">Filtrovat podle:</label>
		<select id="filterType" class="form-control" bind:value={filterType}>
			<option value="movie">Film</option>
			<option value="branch">Pobočka</option>
		</select>
	</div>
	<div class="form-group">
		<label for="filterValue">Hodnota filtru:</label>
		<input id="filterValue" class="form-control" type="text" bind:value={filterValue} on:input={applyFilter} />
	</div>
	<ProjectionTable
		projections={$filteredProjections}
		changeSort={changeSort}
		sortBy={sortBy}
	/>
	<div class="form-group">
		<label for="projection">Projekce:</label>
		<select id="projection" class="form-control" bind:value={selectedProjection} on:change={loadSeats}>
			<option value="">--Vyberte projekci--</option>
			{#each $filteredProjections as projection}
				{#if projection.movie && projection.movie.title}
					<option value={projection.id}>
						{projection.movie.title} - {new Date(projection.startTime).toLocaleString()}
					</option>
				{:else}
					<option value={projection.id}>
						Neznámý film - {new Date(projection.startTime).toLocaleString()}
					</option>
				{/if}
			{/each}
		</select>
	</div>
	<SeatGrid {seats} bind:selectedSeats={$selectedSeats} />
	<form on:submit|preventDefault={createReservation}>
		<div class="form-group">
			<label for="isPaid">Zaplaceno:</label>
			<input id="isPaid" type="checkbox" bind:checked={isPaid} />
		</div>
		<div class="form-group">
			<label for="discount">Sleva:</label>
			<input id="discount" type="number" min="0" max="100" bind:value={discount} class="form-control" />
		</div>
		<button type="submit" class="btn btn-success">Vytvořit rezervaci</button>
	</form>
</div>

<style>
    .container {
        max-width: 800px;
        margin: auto;
    }

    .mt-4 {
        margin-top: 1.5rem;
    }

    .btn {
        margin-top: 1rem;
    }
</style>