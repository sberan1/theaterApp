<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';

	let title = '';
	let durationInMinutes = '';
	let rating = '';
	let genre = '';
	let coverImageUrl = '';
	let showModal = false;

	async function addMovie(movie) {
		const response = await axiosInstance.post('http://localhost:8081/admin/movie', movie);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			const movie = { title, durationInMinutes, rating, genre, coverImageUrl };
			await addMovie(movie);
			showModal = true;
			setTimeout(() => {
				showModal = false;
				goto('/admin');
			}, 1500);
		} catch (error) {
			console.error('An error occurred:', error);
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>Pridat film</h1>
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

		<button type="submit" class="btn btn-primary">Pridat film</button>
	</form>

	{#if showModal}
		<div class="success-dialog">
			Film vytvoren uspesne <br>
			Presmerovavam do administrace...
		</div>
	{/if}
</div>
