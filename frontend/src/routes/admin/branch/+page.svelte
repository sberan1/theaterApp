<script xmlns="http://www.w3.org/1999/html">
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import { onMount } from 'svelte';

	let name = '';
	let address = '';
	let showSuccessDialog = false;
	let branch;
	let version;

	onMount(async () => {
		if(branchId){
			const responseBranch = await axiosInstance.get(`http://localhost:8081/branch/${branchId}`);
			branch = responseBranch.data;
			name = branch.name;
			address = branch.address;
			version = branch.version;
		}
	});

	export let branchId = $page.url.searchParams.get('id');

	async function createBranch(branch) {
		const response = await axiosInstance.post('http://localhost:8081/admin/branch', branch);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
					const branchData = { name, address, version };
					if (branchId) {
						await axiosInstance.put(`http://localhost:8081/admin/branch/${branchId}`, branchData);
					} else {
						await createBranch(branchData);
					}
			showSuccessDialog = true;
			setTimeout(() => {
				showSuccessDialog = false;
				goto('/admin');
			},1500);
		} catch (error) {
			alert('Chyba pri vytvareni pobocky!');
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>{branchId ? 'Edit Branch' : 'Create Branch'}</h1>
<form on:submit|preventDefault={handleSubmit}>
	<div class="form-group">
		<label for="name">Name:</label>
		<input id="name" class="form-control" bind:value={name} required />
	</div>

	<div class="form-group">
		<label for="address">Address:</label>
		<input id="address" class="form-control" bind:value={address} required />
	</div>

	<button type="submit" class="btn btn-primary">{branchId ? 'Save Changes' : 'Create Branch'}</button>
</form>

	{#if showSuccessDialog}
		<div class="alert alert-success mt-2" role="alert">
			Pobocka vytvorena uspesne <br>
			Presmerovavam do administrace...
		</div>
	{/if}
	</div>
