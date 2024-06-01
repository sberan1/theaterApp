<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import axiosInstance from '$lib/axios.instance.js';

	let filterType = 'movie';
	let filterValue = '';
	let results = [];
	let movies = [];
	let branches = [];

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

	const fetchProjections = async () => {
		try {
			const response = await axiosInstance.get('/projections', {
				params: {
					filterType: filterType,
					filterValue: filterValue
				}
			});
			results = response.data;
			console.log('Projections loaded:', results);
		} catch (error) {
			console.error('Error loading projections:', error);
		}
	};

	const handleFilterChange = async () => {
		if (filterValue) {
			await fetchProjections();
		}
	};

	onMount(() => {
		loadMovies();
		loadBranches();
	});
</script>

<header>
	<nav>
		<button on:click={() => goto('/login')}>Přihlásit se</button>
		<button on:click={() => goto('/register')}>Zaregistrovat se</button>
		<button on:click={() => goto('/reservation')}>Vytvořit rezervaci</button>
		<button on:click={() => goto('/projection')}>Přidat projekci</button>
	</nav>
</header>

<div>
	<label>
		Filtrovat podle:
		<select bind:value={filterType}>
			<option value="movie">Film</option>
			<option value="branch">Kino</option>
		</select>
	</label>
	{#if filterType === 'movie'}
		<label>
			Vyberte film:
			<select bind:value={filterValue} on:change={handleFilterChange}>
				<option value="">--Vyberte film--</option>
				{#each movies as movie}
					<option value={movie.id}>{movie.title}</option>
				{/each}
			</select>
		</label>
	{:else}
		<label>
			Vyberte kino:
			<select bind:value={filterValue} on:change={handleFilterChange}>
				<option value="">--Vyberte kino--</option>
				{#each branches as branch}
					<option value={branch.id}>{branch.name}</option>
				{/each}
			</select>
		</label>
	{/if}
</div>

<ul>
	{#each results as result}
		<li>
			{#if filterType === 'branch'}
				{result.movie.title} - {result.startTime} - {result.movie.genre} - {result.movie.durationInMinutes} min
			{/if}
		</li>
	{/each}
</ul>