<script xmlns="http://www.w3.org/1999/html">
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';

	let name = '';
	let address = '';
	let showSuccessDialog = false;

	async function createRoom(branch) {
		const response = await axiosInstance.post('http://localhost:8081/admin/branch', branch);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			const branch = { name, address };
			await createRoom(branch);
			showSuccessDialog = true;
			setTimeout(() => {
				showSuccessDialog = false;
				goto('/admin');
			},1500);
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
	<h1>Vytvorit Pobocku</h1>
<form on:submit|preventDefault={handleSubmit}>
	<div class="form-group">
		<label for="name">Name:</label>
		<input id="name" class="form-control" bind:value={name} required />
	</div>

	<div class="form-group">
		<label for="address">Address:</label>
		<input id="address" class="form-control" bind:value={address} required />
	</div>

	<button type="submit" class="btn btn-primary">Create Room</button>
</form>

	{#if showSuccessDialog}
		<div class="success-dialog">
			Room created successfully! <br>
			Redirecting to admin page...
		</div>
	{/if}
	</div>
