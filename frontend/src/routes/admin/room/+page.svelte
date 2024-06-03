<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

	let name = '';
	let capacity = '';
	let branch = '';
	let version = '';
	let showSuccessDialog = false;
	let branches = [];
	let room;

	export let roomId = $page.url.searchParams.get('id');

	onMount(async () => {
		const responseBranches = await axiosInstance.get('http://localhost:8081/branches');
		branches = responseBranches.data;

		if (roomId) {
			const responseRoom = await axiosInstance.get(`http://localhost:8081/room/${roomId}`);
			room = responseRoom.data;
			name = room.name;
			capacity = room.capacity;
			branch = room.branch.id;
			version = room.version;
		}
		console.log(branches);
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
			const roomData = { name, capacity, branchId: branch, version };

			if (roomId) {
				await axiosInstance.put(`http://localhost:8081/admin/room/${roomId}`, roomData);
			} else {
				await createRoom(roomData);
			}

			showSuccessDialog = true;
			setTimeout(() => {
				showSuccessDialog = false;
				goto('/admin');
			}, 1500);
		} catch (error) {
			alert('Chyba pri vytvareni salu!');
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>{roomId ? 'Edit Room' : 'Create Room'}</h1>
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

		<button type="submit" class="btn btn-primary">{roomId ? 'Save Changes' : 'Create Room'}</button>
	</form>

	{#if showSuccessDialog}
		<div class="alert alert-success mt-2" role="alert">
			Sal vytvoren uspesne <br>
			Presmerovavam do administrace...
		</div>
	{/if}
</div>
