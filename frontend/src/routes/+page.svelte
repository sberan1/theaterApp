<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { writable } from 'svelte/store';
	import axiosInstance from '$lib/axios.instance.js';

	let filterType = 'movie';
	let filterValue = '';
	let results = [];
	let movies = [];
	let branches = [];
	let loading = writable(false);
	let username = '';
	let loggedIn = writable(false);

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
			loading.set(true);
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
		} finally {
			loading.set(false);
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

<div class="container mt-4">
	<div class="form-group">
		<label for="filterType">Filtrovat podle:</label>
		<select id="filterType" class="form-control" bind:value={filterType}>
			<option value="movie">Film</option>
			<option value="branch">Kino</option>
		</select>
	</div>

	{#if filterType === 'movie'}
		<div class="form-group">
			<label for="filterValueMovie">Vyberte film:</label>
			<select id="filterValueMovie" class="form-control" bind:value={filterValue} on:change={handleFilterChange}>
				<option value="">--Vyberte film--</option>
				{#each movies as movie}
					<option value={movie.id}>{movie.title}</option>
				{/each}
			</select>
		</div>
	{:else}
		<div class="form-group">
			<label for="filterValueBranch">Vyberte kino:</label>
			<select id="filterValueBranch" class="form-control" bind:value={filterValue} on:change={handleFilterChange}>
				<option value="">--Vyberte kino--</option>
				{#each branches as branch}
					<option value={branch.id}>{branch.name}</option>
				{/each}
			</select>
		</div>
	{/if}
</div>

<div class="container mt-4">
	{#if $loading}
		<p>Načítání...</p>
	{:else if results.length > 0}
		<ul class="list-group">
			{#each results as result}
				<li class="list-group-item d-flex justify-content-between align-items-center">
					<img src={result.movie.coverImageUrl} alt="{result.movie.title}" class="img-thumbnail" style="max-width: 100px; max-height: 100px;"/>
					<div class="ml-4">
						<h5>{result.movie.title}</h5>
						<p>{new Date(result.startTime).toLocaleString()}</p>
						<p>{result.movie.genre}</p>
						<p>{result.movie.durationInMinutes} min</p>
					</div>
				</li>
			{/each}
		</ul>
	{:else}
		<p>Tento film se momentálně nevysílá.</p>
	{/if}
</div>

<style>
    .navbar-text {
        font-size: 1.25rem;
    }
</style>
