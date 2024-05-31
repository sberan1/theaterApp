<script lang="ts">
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import Cookies from 'js-cookie';
	import jwt from 'jsonwebtoken';

	let isLoading = true;

	async function checkUser() {
		const token = Cookies.get('token');

		if (!token) {
			await goto("/login");
		}
		isLoading = false;
		const some = jwt.decode(token);
		console.log(some);
	}

	onMount(checkUser)

</script>


<svelte:head>
	<title>Admin Dashboard</title>
</svelte:head>

{#if isLoading}
	<p>Loading...</p>
{:else}
	<slot/>
{/if}
