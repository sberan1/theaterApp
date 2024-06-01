<script>
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';

	let filterType = 'movie';
	let filterValue = '';
	let projections = [];
	let selectedProjection = '';
	let isPaid = false;
	let discount = 0;

	const loadProjections = async () => {
		try {
			const response = await axiosInstance.get('/projections', {
				params: {
					filterType: filterType,
					filterValue: filterValue
				},
				headers: {
					Authorization: `Bearer ${Cookies.get('token')}`
				}
			});
			projections = response.data;
			console.log('Projections loaded:', projections);
		} catch (error) {
			console.error('Error loading projections:', error);
		}
	};

	const createReservation = async () => {
		try {
			const token = Cookies.get('token');
			if (!token) {
				await goto('/login');
				return;
			}
			const user = await parseJwt(token);

			const reservationData = {
				userId: user.id,
				projectionId: selectedProjection,
				paid: isPaid,
				discount
			};

			const response = await axiosInstance.post('/reservation', reservationData, {
				headers: {
					Authorization: `Bearer ${token}`
				}
			});
			console.log('Reservation created:', response.data);
			goto('/');
		} catch (error) {
			console.error('Error creating reservation:', error);
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
	<nav>
		<button on:click={() => goto('/')}>Vrať se zpět</button>
	</nav>
</header>

<div>
	<h3>Vytvořit rezervaci</h3>
	<form on:submit|preventDefault={createReservation}>
		<label>
			Projekce:
			<select bind:value={selectedProjection}>
				<option value="">--Vyberte projekci--</option>
				{#each projections as projection}
					<option value={projection.id}>
						{projection.movie.title} - {projection.startTime}
					</option>
				{/each}
			</select>
		</label>
		<label>
			Zaplaceno:
			<input type="checkbox" bind:checked={isPaid} />
		</label>
		<label>
			Sleva:
			<input type="number" min="0" max="100" bind:value={discount} />
		</label>
		<button type="submit">Vytvořit rezervaci</button>
	</form>
</div>