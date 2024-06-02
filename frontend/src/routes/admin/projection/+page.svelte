<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';


	let version;
	let startTime = '';
	let priceType = '';
	let movie = '';
	let room = '';
	let branch = '';
	let projection;
	let showModal = false;
	let branches = [];
	let rooms = [];
	let movies = [];
	let priceTypes = [];

	export let projectionId = $page.url.searchParams.get('id');


	onMount(async () => {
		const responseBranches = await axiosInstance.get('http://localhost:8081/branches');
		branches = responseBranches.data;

		const responseMovies = await axiosInstance.get('http://localhost:8081/movies');
		movies = responseMovies.data;

		const responsePriceTypes = await axiosInstance.get('http://localhost:8081/prices');
		priceTypes = responsePriceTypes.data;

		if(projectionId){
		const responseProjection = await axiosInstance.get(`/user/projection/${projectionId}` );
		projection = responseProjection.data;
		console.log(projection);

		startTime = projection.startTime;
		priceType = projection.priceType.id;
		movie = projection.movie.id;
		branch = projection.room.branch.id;
		await loadRooms();
		room = projection.room.id;
		version = projection.version;
	}
	});

	const loadRooms = async () => {
		const response = await axiosInstance.get(`http://localhost:8081/rooms?branchId=${branch}`);
		rooms = response.data;
	};

	async function createProjection(projection) {
		const response = await axiosInstance.post('http://localhost:8081/admin/projection', projection);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			if (projectionId) {
				const projection = { startTime, priceTypeId: priceType, movieId: movie, roomId: room, version: version};
				await axiosInstance.put(`http://localhost:8081/admin/projection/${ projectionId}`, projection);
				showModal = true;
				setTimeout(() => {
					showModal = false;
					goto('/admin');
				}, 1500);
				return;
			}
			const projection = { startTime, priceTypeId: priceType, movieId: movie, roomId: room };
			await createProjection(projection);
			showModal = true;
			setTimeout(() => {
				showModal = false;
				goto('/admin');
			}, 1500);
		} catch (error) {
			alert('Chyba pri vytvareni promitani, zkus restartovat stranku!');
			//console.error('An error occurred:', error);
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>Create Projection</h1>
	<form on:submit|preventDefault={handleSubmit}>
		<div class="form-group">
			<label for="startTime">Start Time:</label>
			<input id="startTime" class="form-control" type="datetime-local" bind:value={startTime} required />
		</div>

		<div class="form-group">
			<label for="priceType">Price Type:</label>
			<select id="priceType" class="form-control" bind:value={priceType} required>
				<option value="">-- Select a price type --</option>
				{#each priceTypes as priceTypeOption (priceTypeOption.id)}
					<option value={priceTypeOption.id}>{priceTypeOption.name} | {priceTypeOption.value}</option>
				{/each}
			</select>
		</div>

		<div class="form-group">
			<label for="movie">Movie:</label>
			<select id="movie" class="form-control" bind:value={movie} required>
				<option value="">-- Select a movie --</option>
				{#each movies as movieOption (movieOption.id)}
					<option value={movieOption.id}>{movieOption.title}</option>
				{/each}
			</select>
		</div>

		<div class="form-group">
			<label for="branch">Branch:</label>
			<select id="branch" class="form-control" bind:value={branch} on:change={loadRooms} required>
				<option value="">-- Select a branch --</option>
				{#each branches as branchOption (branchOption.id)}
					<option value={branchOption.id}>{branchOption.name}</option>
				{/each}
			</select>
		</div>

		<div class="form-group">
			<label for="room">Room:</label>
			<select id="room" class="form-control" bind:value={room} required>
				<option value="">-- Select a room --</option>
				{#each rooms as roomOption (roomOption.id)}
					<option value={roomOption.id}>{roomOption.name}</option>
				{/each}
			</select>
		</div>
		{#if projectionId}
			<button type="submit" class="btn btn-primary">Save Changes</button>
			{:else}
		<button type="submit" class="btn btn-primary">Create Projection</button>
		{/if}


	</form>

	{#if showModal}
		<div class="alert alert-success mt-2" role="alert">
		  Promitani vytvoreno uspesne <br>
			Presmerovavam do administrace...
		</div>
	{/if}
</div>
