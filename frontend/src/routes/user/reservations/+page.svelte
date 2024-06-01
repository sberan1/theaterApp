<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';
	import { goto } from '$app/navigation';

	let reservations = [];

	const loadReservations = async () => {
		try {
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
		}
	};

	async function parseJwt(token) {
		const base64Url = token.split('.')[1];
		const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
		const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
			return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
		}).join(''));

		return JSON.parse(jsonPayload);
	}

	onMount(loadReservations);
</script>

<header>
	<nav>
		<button on:click={() => goto('/')}>Vrať se zpět</button>
	</nav>
</header>

<div>
	<h3>Moje rezervace</h3>
	<table>
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
				<td>{reservation.projection.movie.title}</td>
				<td>{new Date(reservation.projection.startTime).toLocaleString()}</td>
				<td>{reservation.paid ? 'Ano' : 'Ne'}</td>
				<td>{reservation.discount}%</td>
			</tr>
		{/each}
		</tbody>
	</table>
</div>