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
		const some = await parseJwt(token);
		console.log(some);
		if (some.role !== 'ADMIN') {
			await goto("/");
		}
		isLoading = false;

	}
	async function parseJwt (token : string) {
		const base64Url = token.split('.')[1];
		const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
		const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
			return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
		}).join(''));

		return JSON.parse(jsonPayload);
	};

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
