<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

	let title = '';
	let durationInMinutes = '';
	let rating = '';
	let genre = '';
	let coverImageUrl = '';
	let showSuccessDialog = false;
	let movie;

	export let movieId = $page.url.searchParams.get('id');

	onMount(async () => {
		if(movieId){
			const responseMovie = await axiosInstance.get(`http://localhost:8081/movie/${movieId}`);
			movie = responseMovie.data;
			title = movie.title;
			durationInMinutes = movie.durationInMinutes;
			rating = movie.rating;
			genre = movie.genre;
			coverImageUrl = movie.coverImageUrl;
		}
	});

	async function createMovie(movie) {
		const response = await axiosInstance.post('http://localhost:8081/admin/movie', movie);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			const movieData = { title, durationInMinutes, rating, genre, coverImageUrl };

			if (movieId) {
				await axiosInstance.put(`http://localhost:8081/admin/movie/${movieId}`, movieData);
			} else {
				await createMovie(movieData);
			}

			showSuccessDialog = true;
			setTimeout(() => {
				showSuccessDialog = false;
				goto('/admin');
			}, 1500);
		} catch (error) {
			alert('Chyba pri pridavani filmu');
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>{movieId ? 'Edit Movie' : 'Add Movie'}</h1>
	<form on:submit|preventDefault={handleSubmit}>
		<div class="form-group">
			<label for="title">Title:</label>
			<input id="title" class="form-control" bind:value={title} required />
		</div>

		<div class="form-group">
			<label for="durationInMinutes">Duration (in minutes):</label>
			<input id="durationInMinutes" class="form-control" type="number" min="1" bind:value={durationInMinutes} required />
		</div>

		<div class="form-group">
			<label for="rating">Rating:</label>
			<input id="rating" class="form-control" bind:value={rating} required />
		</div>

		<div class="form-group">
			<label for="genre">Genre:</label>
			<input id="genre" class="form-control" bind:value={genre} required />
		</div>

		<div class="form-group">
			<label for="coverImageUrl">Cover Image URL:</label>
			<input id="coverImageUrl" class="form-control" bind:value={coverImageUrl} required />
		</div>

		<button type="submit" class="btn btn-primary">{movieId ? 'Save Changes' : 'Add Movie'}</button>
	</form>

	{#if showSuccessDialog}
		<div class="alert alert-success mt-2" role="alert">
			Film vytvoren uspesne <br>
			Presmerovavam do administrace...
		</div>
	{/if}
</div>
