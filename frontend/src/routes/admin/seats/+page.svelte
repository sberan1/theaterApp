<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { onMount } from 'svelte';

	let seats = [];
	let branches = [];
	let rooms = [];
	let newRow = '';
	let newSeats = '';
	let selectedBranch = '';
	let selectedRoom = '';
	let allSelected = false;
	let showDialog = false;

const seatPlan = async () => {
	const response = await axiosInstance.get(`/seats?roomId=${selectedRoom}`);
	seats = response.data;
	console.log(seats);
	};

	const addSeats = async () => {
		const response = await axiosInstance.post(`/admin/addRow?row=${newRow}&numberOfSeats=${newSeats}&roomId=${selectedRoom}`);
		seats = response.data;
		newRow = '';
		newSeats = '';
	};

	onMount(async () => {
		const responseBranches = await axiosInstance.get('http://localhost:8081/branches');
		branches = responseBranches.data;
	});

	const loadRooms = async () => {
		const response = await axiosInstance.get(`http://localhost:8081/rooms?branchId=${selectedBranch}`);
		rooms = response.data;
	};

	const handleBranchChange = () => {
		loadRooms();
	};

	const handleRoomChange = () => {
	seatPlan()
	};
</script>

<div class="container">
	<h1>Plán sedadel</h1>
	<h1>Upravit Sedadla</h1>
	<div class="form-group">
		<label for="branch">Pobočka:</label>
		<select id="branch" class="form-control" bind:value={selectedBranch} on:change={handleBranchChange} required>
			<option value="">-- Vyberte pobočku --</option>
			{#each branches as branch (branch.id)}
			<option value={branch.id}>{branch.name}</option>
			{/each}
		</select>
	</div>
	<div class="form-group">
		<label for="room">Místnost:</label>
		<select id="room" class="form-control" bind:value={selectedRoom} on:change={handleRoomChange} required>
			<option value="">-- Vyberte místnost --</option>
			{#each rooms as room (room.id)}
			<option value={room.id}>{room.name}</option>
			{/each}
		</select>
	</div>
	<h2>Přidat nová sedadla</h2>
	<form on:submit|preventDefault={addSeats}>
		<div class="form-group">
			<label for="newRow">Řada:</label>
			<input id="newRow" class="form-control" bind:value={newRow} required />
		</div>
		<div class="form-group">
			<label for="newSeats">Počet sedadel:</label>
			<input id="newSeats" class="form-control" type="number" bind:value={newSeats} required />
		</div>
		<button type="submit" class="btn btn-primary">Přidat sedadla</button>
	</form>

	{#each seats as seat (seat.id)}
		<div class="seat">
			<p>Řada: {seat.roomRow}</p>
			<p>Počet sedadel: {seat.seatNumber}</p>
		</div>
	{/each}

	{#if showDialog==true}
		<div class="alert alert-danger mt-2" role="alert">
			Je potreba vybrat pobočku a místnost
		</div>
	{/if}

</div>
