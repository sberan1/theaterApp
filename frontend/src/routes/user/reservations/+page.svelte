<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';
	import { goto } from '$app/navigation';
	import { writable } from 'svelte/store';

	let reservations = [];
	let loading = writable(false); // Stav načítání

	const loadReservations = async () => {
		try {
			loading.set(true); // Načítání true
			const token = Cookies.get('token');
			if (!token) {
				await goto('/login');
				return;
			}

			const user = await parseJwt(token);

			const response = await axiosInstance.get('/reservations', {
				params: { username: user.sub },
				headers: { 'Authorization': `Bearer ${token}` }
			});

			reservations = response.data;
			console.log('Reservations loaded:', reservations);
		} catch (error) {
			console.error('Error loading reservations:', error);
		} finally {
			loading.set(false); // Načítání false
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
			<button class="btn btn-primary mr-2" on:click={() => goto('/')}>Vrať se zpět</button>
		</div>
	</nav>
</header>

<div class="container mt-4">
	<h3>Moje rezervace</h3>
	{#if $loading}
		<p>Načítání...</p>
	{:else}
		<table class="table">
			<thead>
			<tr>
				<th>Film</th>
				<th>Čas</th>
				<th>Zaplaceno</th>
				<th>Sleva</th>
			</tr>
			</thead>
			<tbody>
			{#each reservations as reservation}
				<tr>
					<td>{reservation.projection.movie ? reservation.projection.movie.title : 'Neznámý film'}</td>
					<td>{new Date(reservation.projection.startTime).toLocaleString()}</td>
					<td>{reservation.paid ? 'Ano' : 'Ne'}</td>
					<td>{reservation.discount}%</td>
				</tr>
			{/each}
			</tbody>
		</table>
	{/if}
</div>