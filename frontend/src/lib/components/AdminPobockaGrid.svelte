<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';

	let branches = [];

	const loadData = async () => {
		const response = await axiosInstance.get('http://localhost:8081/branches');
		branches = response.data;
	};
	const handleDelete = (id) => async () => {
		await axiosInstance.delete(`http://localhost:8081/branches/${id}`);
		await loadData();
	};

	onMount(async () => {
		await loadData()
	});
</script>

<div class="container">
	<div class="row">
		{#each branches as branch (branch.id)}
			<div class="col-md-4">
				<div class="card mt-4">
					<div class="card-body">
						<h5 class="card-title">{branch.name}</h5>
						<p class="card-text">{branch.address}</p>
						<div	class="d-flex content-space-between">
						<a href="/admin/branch?id={branch.id}" class="btn btn-secondary">Edit Branch</a>
						<button class="btn btn-danger" on:click={handleDelete(branch.id)}>Delete Branch</button>
						</div>
					</div>
				</div>
			</div>
		{/each}
	</div>
</div>
