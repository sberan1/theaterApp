<script lang="ts">
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance';
	import { writable } from 'svelte/store';

	let isLoading = true;
	let isOffline = writable(false);

	async function checkConnection() {
		try {
			await axiosInstance.get('/health-check');
			isOffline.set(false);
			console.log("Connection is okay");
		} catch (error) {
			isOffline.set(true);
		}
	}

	onMount(() => {
		setInterval(checkConnection, 5000);
		isLoading = false;
	});
</script>

<svelte:head>
	<title>My App</title>
</svelte:head>

{#if isLoading}
	<p>Loading...</p>
{:else}
	{#if $isOffline}
		<div class="alert alert-danger" role="alert">
			Ztráta připojení k serveru. Zkontrolujte své připojení k internetu.
		</div>
	{/if}
	<slot/>
{/if}