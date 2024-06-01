<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import axiosInstance from '$lib/axios.instance.js';
	import { writable } from 'svelte/store';

	let movies = [];
	let branches = [];
	let newProjection = writable({
		startTime: '',
		priceTypeId: '',
		movieId: '',
		roomId: ''
	});

	const loadMovies = async () => {
		try {
			const response = await axiosInstance.get('/movies');
			movies = response.data;
			console.log('Movies loaded:', movies);
		} catch (error) {
			console.error('Error loading movies:', error);
		}
	};

	const loadBranches = async () => {
		try {
			const response = await axiosInstance.get('/branches');
			branches = response.data;
			console.log('Branches loaded:', branches);
		} catch (error) {
			console.error('Error loading branches:', error);
		}
	};

	const addProjection = async () => {
		try {
			const projection = $newProjection;
			const response = await axiosInstance.post('/projection', projection);
			console.log('Projection added:', response.data);
			goto('/admin');
		} catch (error) {
			console.error('Error adding projection:', error);
		}
	};

	onMount(() => {
		loadMovies();
		loadBranches();
	});
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>

<div>
	<h3>Přidat novou projekci</h3>
	<form on:submit|preventDefault={addProjection}>
		<label>
			Čas začátku:
			<input type="datetime-local" bind:value={$newProjection.startTime} />
		</label>
		<label>
			Typ ceny:
			<input type="text" bind:value={$newProjection.priceTypeId} />
		</label>
		<label>
			Film:
			<select bind:value={$newProjection.movieId}>
				<option value="">--Vyberte film--</option>
				{#each movies as movie}
					<option value={movie.id}>{movie.title}</option>
				{/each}
			</select>
		</label>
		<label>
			Místnost:
			<input type="text" bind:value={$newProjection.roomId} />
		</label>
		<button type="submit">Přidat projekci</button>
	</form>
</div>
