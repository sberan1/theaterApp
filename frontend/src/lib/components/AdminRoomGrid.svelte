<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';

	let rooms = [];
	let branches = [];
	let selectedBranch = '';

	const loadRooms = async () => {
		if (selectedBranch === '') {
			const response = await axiosInstance.get(`/rooms`);
			rooms = response.data;
			return;
		}
		const response = await axiosInstance.get(`/rooms?branchId=${selectedBranch}`);
		rooms = response.data;
	};

	const deleteRoom = (roomId) => async () => {
		await axiosInstance.delete(`/admin/room/${roomId}`);
		await loadRooms();
	};

	const loadBranches = async () => {
		const response = await axiosInstance.get('http://localhost:8081/branches');
		branches = response.data;
	};

	onMount(async () => {
		await loadBranches();
		await loadRooms();
		console.log("rooms: " + rooms);
	});

	const handleBranchChange = async () => {
		await loadRooms();
	};
</script>

<div class="container">
	<div class="form-group">
		<label for="branch">Branch:</label>
		<select id="branch" class="form-control" bind:value={selectedBranch} on:change={handleBranchChange}>
			<option value="">-- Select a branch --</option>
			{#each branches as branch (branch.id)}
				<option value={branch.id}>{branch.name}</option>
			{/each}
		</select>
	</div>

	<div class="row">
		{#each rooms as room (room.id)}
			<div class="col-md-4">
				<div class="card mt-4">
					<div class="card-body ">
						<h5 class="card-title">{room.name}</h5>
						<p class="card-text">Branch: {room.branch.name}</p>
						<p class="card-text">Capacity: {room.capacity}</p>
						<a href="/admin/room?id={room.id}" class="btn btn-secondary">Edit Room</a>
						<button class="btn btn-danger" on:click={deleteRoom(room.id)}>Delete Room</button>
					</div>
				</div>
			</div>
		{/each}
	</div>
</div>
