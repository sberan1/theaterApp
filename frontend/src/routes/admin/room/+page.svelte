<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';

	let name = '';
	let capacity = '';
	let branch = '';
	let showModal = false;
	let branches = [];

	onMount(async () => {
		const response = await axiosInstance.get('http://localhost:8081/branches');
		branches = response.data;
	});

	async function createRoom(room) {
		const response = await axiosInstance.post('http://localhost:8081/admin/room', room);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			const room = { name, capacity, branchId: branch };
			console.log(room);
			await createRoom(room);
			showModal = true;
			setTimeout(() => {
				showModal = false;
				goto('/admin');
			}, 1000);
		} catch (error) {
			alert('chyba pri vytvareni kinosalu');
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}> Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>Vytvorit Sal</h1>
	<form on:submit|preventDefault={handleSubmit}>
		<div class="form-group">
			<label for="name">Name:</label>
			<input id="name" class="form-control" bind:value={name} required />
		</div>

		<div class="form-group">
			<label for="capacity">Capacity:</label>
			<input id="capacity" class="form-control" type="number" bind:value={capacity} required />
		</div>

		<div class="form-group">
			<label for="branch">Branch:</label>
			<select id="branch" class="form-control" bind:value={branch} required>
				<option value="">-- Select a branch --</option>
				{#each branches as branchOption (branchOption.id)}
					<option value={branchOption.id}>{branchOption.name}</option>
				{/each}
			</select>
		</div>

		<button type="submit" class="btn btn-primary">Create Room</button>
	</form>

	{#if showModal}
		<div class="alert alert-success mt-2" role="alert">
			Sal vytvoren uspesne <br>
			Presmerovavam do administrace...
		</div>
	{/if}
</div>
