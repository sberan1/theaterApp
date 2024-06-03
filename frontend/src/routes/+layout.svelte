<script lang="ts">
	import { onMount } from 'svelte';
	import { page } from '$app/stores';
	import axiosInstance from '$lib/axios.instance';
	import { writable } from 'svelte/store';
	import Cookies from 'js-cookie';
	import { goto } from '$app/navigation';

	let isLoading = true;
	let isOffline = writable(false);
	let loggedIn = writable(false);
	let username = '';

	async function checkConnection() {
		try {
			await axiosInstance.get('/health-check');
			isOffline.set(false);
			console.log("Connection is okay");
		} catch (error) {
			isOffline.set(true);
		}
	}

	const checkLogin = async () => {
		const token = Cookies.get('token');
		if (token) {
			try {
				const user = await parseJwt(token);
				username = user.sub;
				loggedIn.set(true);
			} catch (error) {
				console.error('Error parsing token:', error);
			}
		}
	};

	async function parseJwt(token) {
		const base64Url = token.split('.')[1];
		const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
		const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
			return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
		}).join(''));

		return JSON.parse(jsonPayload);
	}

	const logout = () => {
		Cookies.remove('token');
		loggedIn.set(false);
		username = '';
		goto('/login');
	};

	onMount(() => {
		setInterval(checkConnection, 5000);
		checkLogin();
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
	{#if $page.route.id}
	<header class="{$page.route.id ?? ''}">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container d-flex justify-content-between align-items-center">
				<div>
					{#if $loggedIn}
					<span class="navbar-text mr-3">
						<i class="fas fa-user-circle"></i> {username}
					</span>
						<button class="btn btn-danger mr-2" on:click={logout}>Odhlásit se</button>
						<button class="btn btn-info mr-2" on:click={() => goto('/user/account')}>Můj účet</button>
					{:else}
						<button class="btn btn-primary mr-2" on:click={() => goto('/login')}>Přihlásit se</button>
						<button class="btn btn-secondary mr-2" on:click={() => goto('/register')}>Zaregistrovat se</button>
					{/if}
				</div>
			</div>
		</nav>
	</header>
	{/if}
	<slot/>
{/if}
