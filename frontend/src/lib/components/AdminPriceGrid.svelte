<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';

	let prices = [];
	let page = 0; // start from the first page
	let limit = 12; // number of items per page

	const handleDetele = async (id) => {
			await axiosInstance.delete(`/admin/price/${id}`);
			loadData();
		};


	const loadData = async () => {
		const response = await axiosInstance.get(`/prices?page=${page}&limit=${limit}`);
		prices = response.data;
	};

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

	onMount(loadData);
</script>

<div class="container">
	<div class="row">
		{#each prices as price (price.id)}
			<div class="col-md-4">
				<div class="card mt-4">
					<div class="card-body">
						<h5 class="card-title">{price.name}</h5>
						<p class="card-text">Value: {price.value}</p>
						<a href="/admin/price?id={price.id}" class="btn btn-secondary">Edit Price</a>
						<button	 class="btn btn-danger" on:click={handleDetele(price.id)}>Delete Price</button>
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
