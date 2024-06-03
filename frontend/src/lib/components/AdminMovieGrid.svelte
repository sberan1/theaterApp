<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';

	let movies = [];
	let page = 0;
	let size = 12;

	const loadData = async () => {
		const response = await axiosInstance.get(`/movies?page=${page}&size=${size}`);
		movies = response.data;
	};

	const handleDelete = (id) => async () => {
		await axiosInstance.delete(`/admin/movie/${id}`);
		loadData();
	};

	onMount(async () => {
		loadData();
	});

	const nextPage = () => {
		page++;
		loadData();
	};

	const prevPage = () => {
		if (page > 0) {
			page--;
			loadData();
		}
	};
</script>

<div class="container">
	<div class="row">
		{#each movies as movie (movie.id)}
			<div class="col-md-4">
				<div class="card mt-4">
					<img src={movie.coverImageUrl} class="card-img-top" alt={movie.title}>
					<div class="card-body">
						<h5 class="card-title">{movie.title}</h5>
						<p class="card-text">Duration: {movie.durationInMinutes} minutes</p>
						<p class="card-text">Rating: {movie.rating}</p>
						<p class="card-text">Genre: {movie.genre}</p>
						<a href="/admin/movie?id={movie.id}" class="btn btn-secondary">Edit Movie</a>
						<button class="btn btn-danger" on:click={handleDelete(movie.id)}>Delete Movie</button>
					</div>
				</div>
			</div>
		{/each}
	</div>
	<div class="mt-4">
	<button class="btn btn-info" on:click={prevPage}>Previous Page</button>
	<button class="btn btn-primary" on:click={nextPage}>Next Page</button>
	</div>
</div>
