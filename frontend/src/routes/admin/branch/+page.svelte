<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';

	let name = '';
	let address = '';

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
			goto('/admin'); // Redirect to admin page after successful creation
		} catch (error) {
			console.error('An error occurred:', error);
		}
	}
</script>
<div class="container">
	<h1>Create Room</h1>
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
	</div>
