<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

	let name = '';
	let value = '';
	let version;
	let showSuccessDialog = false;
	let price;

	export let priceId = $page.url.searchParams.get('id');

	onMount(async () => {
		if(priceId){
			const responsePrice = await axiosInstance.get(`/price/${priceId}`);
			price = responsePrice.data;
			name = price.name;
			value = price.value;
			version = price.version;
		}
	});

	async function createPrice(price) {
		const response = await axiosInstance.post('/admin/price', price);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			const priceData = { name, value, version };

			if (priceId) {
				await axiosInstance.put(`/admin/price/${priceId}`, priceData);
			} else {
				await createPrice(priceData);
			}

			showSuccessDialog = true;
			setTimeout(() => {
				showSuccessDialog = false;
				goto('/admin');
			}, 1500);
		} catch (error) {
			alert('Chyba pri pridavani ceny');
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>{priceId ? 'Edit Price' : 'Add Price'}</h1>
	<form on:submit|preventDefault={handleSubmit}>
		<div class="form-group">
			<label for="name">Název:</label>
			<input id="name" class="form-control" bind:value={name} required />
		</div>

		<div class="form-group">
			<label for="value">Hodnota:</label>
			<input id="value" class="form-control" type="number" min="0" step="0.01" bind:value={value} required />
		</div>

		<button type="submit" class="btn btn-primary">{priceId ? 'Save Changes' : 'Add Price'}</button>
	</form>

	{#if showSuccessDialog}
		<div class="alert alert-success mt-2" role="alert">
			Typ ceny byl úspěšně přidán <br>
			Přesměrování do administrace...
		</div>
	{/if}
</div>
