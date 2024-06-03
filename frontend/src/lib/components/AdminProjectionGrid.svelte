<script>
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';

	let projections = [];
	let sortBy = 'startTime';
	let page = 0;
	let limit = 20;


	onMount(loadData);
	$: sortBy, loadData();
	$: page, loadData();
	$: limit, loadData();

	async function loadData() {
		const res = await axiosInstance.get(`/projections?sortBy=${sortBy}&page=${page}&limit=${limit}`);
		projections = res.data;
		console.log(res);
	}

	const handleEdit = async (id) =>  {
		goto(`/admin/projection?id=${id}`);
	}

	const handleDelete = async (id) => {
		try {
			await axiosInstance.delete(`/admin/projection/${id}`)
			await loadData();
		} catch (e) {
			alert('Failed to delete projection');
		}
	}

	function changeSort(newSortBy) {
		sortBy = newSortBy;
	}

	function nextPage() {
		page++;
	}

	function prevPage() {
		if (page > 1) {
			page--;
		}
	}
</script>



<table class="table">
	<thead>
	<tr>
		<th on:click={() => changeSort('startTime')}>
			Start Time {sortBy === 'startTime' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('movie.title')}>
			Movie Title {sortBy === 'movie.title' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('priceType')}>
			Price Type {sortBy === 'priceType' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('room.name')}>
			Room Name {sortBy === 'room.name' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('room.branch.name')}>
			Branch Name {sortBy === 'room.branch.name' ?  '↓' : ''}
		</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	</thead>
	<tbody>
	{#each projections as projection}
		<tr>
			<td>
				{new Date(projection.startTime).toLocaleDateString('cs-CZ', {
					year: 'numeric',
					month: 'long',
					day: 'numeric',
					hour: '2-digit',
					minute: '2-digit'
				})}
			</td>
			<td>{projection.movie ? projection.movie.title : 'N/A'}</td>
			<td>{projection.priceType ? projection.priceType.name : 'N/A'}</td>
			<td>{projection.room ? projection.room.name : 'N/A'}</td>
			<td>{projection.room ? projection.room.branch.name : "N/A"}</td>
			<td><button class="btn btn-secondary" on:click={handleEdit(projection.id)}>Edit</button></td>
			<td><button class="btn btn-danger" on:click={handleDelete(projection.id)}>Delete</button></td>
		</tr>
	{/each}
	</tbody>
</table>
<div class="d-flex justify-content-between">
	<button class="btn btn-info" on:click={prevPage}>Previous Page</button>
	<button class="btn btn-primary" on:click={nextPage}>Next Page</button>
</div>
