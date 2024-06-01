<script lang="ts">
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import Cookies from 'js-cookie';

	let isLoading = true;

	async function checkUser() {
		const token = Cookies.get('token');

		if (!token) {
			await goto("/login");
		}
		isLoading = false;

	}

	onMount(checkUser)

</script>

<svelte:head>
	<title>User Dashboard</title>
</svelte:head>

	{#if isLoading}
		<p>Loading...</p>
	{:else}
		<slot />
	{/if}
