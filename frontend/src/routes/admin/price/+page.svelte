<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';

	let name = '';
	let value = '';
	let showModal = false;

	async function addPriceType(priceType) {
		const response = await axiosInstance.post('http://localhost:8081/admin/price', priceType);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data;
	}

	async function handleSubmit() {
		try {
			const priceType = { name, value };
			await addPriceType(priceType);
			showModal = true;
			setTimeout(() => {
				showModal = false;
				goto('/admin');
			}, 1500);
		} catch (error) {
			alert('Chyba při přidávání typu ceny!');
		}
	}
</script>

<header>
	<nav>
		<button on:click={() => goto('/admin')}>Vrať se zpět</button>
	</nav>
</header>
<div class="container">
	<h1>Přidat typ ceny</h1>
	<form on:submit|preventDefault={handleSubmit}>
		<div class="form-group">
			<label for="name">Název:</label>
			<input id="name" class="form-control" bind:value={name} required />
		</div>

		<div class="form-group">
			<label for="value">Hodnota:</label>
			<input id="value" class="form-control" type="number" min="0" step="0.01" bind:value={value} required />
		</div>

		<button type="submit" class="btn btn-primary">Přidat typ ceny</button>
	</form>

	{#if showModal}
		<div class="alert alert-success mt-2" role="alert">
			Typ ceny byl úspěšně přidán <br>
			Přesměrování do administrace...
		</div>
	{/if}
</div>
