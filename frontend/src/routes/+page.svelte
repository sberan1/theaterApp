<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';

	let filterType = 'movie';
	let filterValue = '';
	let results = [];
	let movies = [];
	let theaters = [];

	const loadMovies = async () => {
		try {
			const response = await fetch('http://localhost:8081/movies');
			if (response.ok) {
				movies = await response.json();
				console.log('Movies loaded:', movies); // Debugging výpis
			} else {
				console.error('Failed to load movies:', response.statusText);
			}
		} catch (error) {
			console.error('Error loading movies:', error);
		}
	};

	const loadTheaters = async () => {
		try {
			const response = await fetch('http://localhost:8081/theaters');
			if (response.ok) {
				theaters = await response.json();
				console.log('Theaters loaded:', theaters); // Debugging výpis
			} else {
				console.error('Failed to load theaters:', response.statusText);
			}
		} catch (error) {
			console.error('Error loading theaters:', error);
		}
	};

	const fetchProjections = async () => {
		try {
			const response = await fetch(`http://localhost:8081/projections?filterType=${filterType}&filterValue=${filterValue}`);
			if (response.ok) {
				results = await response.json();
				console.log('Projections loaded:', results); // Debugging výpis
			} else {
				console.error('Failed to load projections:', response.statusText);
			}
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
		loadTheaters();
	});
</script>

<header>
	<nav>
		<button on:click={() => goto('/login')}>Přihlásit se</button>
		<button on:click={() => goto('/register')}>Zaregistrovat se</button>
		<button on:click={() => goto('/reservation')}>Vytvořit rezervaci</button>
	</nav>
</header>

<div>
	<label>
		Filtrovat podle:
		<select bind:value={filterType}>
			<option value="movie">Film</option>
			<option value="theater">Kino</option>
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
				{#each theaters as theater}
					<option value={theater.id}>{theater.name}</option>
				{/each}
			</select>
		</label>
	{/if}
</div>

<ul>
	{#each results as result}
		<li>
			{#if filterType === 'movie'}
				{result.theater.name} - {result.timestamp}
			{:else}
				{result.movie.title} - {result.timestamp}
			{/if}
		</li>
	{/each}
</ul>