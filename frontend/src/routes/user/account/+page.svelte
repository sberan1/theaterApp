<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import { writable } from 'svelte/store';
	import { goto } from '$app/navigation';
	import Cookies from 'js-cookie';

	let userDetails = {
		username: '',
		email: '',
		phoneNumber: '',
		balance: ''
	};
	let userDetailsChanged = {
		username: '',
		email: '',
		phoneNumber: '',
		password: '',
		confirmPassword: ''
	};
	let showEditForm = writable(false);
	let loading = writable(false);

	const loadAccountDetails = async () => {
		try {
			loading.set(true);
			const token = Cookies.get('token');
			if (!token) {
				throw new Error("Token not found");
			}
			const parsedToken = parseJwt(token);
			if (!parsedToken) {
				throw new Error("Token parsing failed");
			}
			const username = parsedToken.sub;
			const response = await axiosInstance.get('/user/account?username=' + username);
			userDetails = response.data;
			userDetailsChanged.username = userDetails.username;
			userDetailsChanged.email = userDetails.email;
			userDetailsChanged.phoneNumber = userDetails.phoneNumber;
			loading.set(false);
		} catch (error) {
			console.error('Error loading account details:', error);
		}
	};

	const parseJwt = (token) => {
		try {
			const parts = token.split('.');
			if (parts.length !== 3) {
				throw new Error("Invalid token format");
			}
			const base64Url = parts[1];
			const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
			const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
				return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
			}).join(''));
			return JSON.parse(jsonPayload);
		} catch (error) {
			console.error('Error parsing token:', error);
			return null;
		}
	};

	const handleEdit = () => {
		showEditForm.set(true);
	};

	const handleSave = async () => {
		try {
			if (userDetailsChanged.password !== userDetailsChanged.confirmPassword) {
				alert("Hesla se neshodují!");
				return;
			}

			const token = Cookies.get('token');
			console.log("Token from cookies (handleSave):", token);
			if (!token) {
				throw new Error("Token not found");
			}
			const parsedToken = parseJwt(token);
			if (!parsedToken) {
				throw new Error("Token parsing failed");
			}
			const username = parsedToken.sub;
			const response = await axiosInstance.put('/user/account?username=' + username, userDetailsChanged);
			userDetailsChanged.password = '';
			userDetailsChanged.confirmPassword = '';
			const newToken = response.data.accessToken;
			Cookies.set('token', newToken, { expires: 1 });
			showEditForm.set(false);
			loadAccountDetails();
		} catch (error) {
			console.error('Error saving account details:', error);
		}
	};

	onMount(loadAccountDetails);
</script>

<div class="container mt-4">
	<h1>Můj účet</h1>
	{#if $loading}
		<p>Načítání...</p>
	{:else}
		<div>
			<h2>Údaje o účtu</h2>
			{#if $showEditForm}
				<form on:submit|preventDefault={handleSave}>
					<div class="form-group">
						<label for="username">Uživatelské jméno:</label>
						<input id="username" class="form-control" type="text" bind:value={userDetailsChanged.username} required />
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<input id="email" class="form-control" type="email" bind:value={userDetailsChanged.email} required />
					</div>
					<div class="form-group">
						<label for="phone">Telefon:</label>
						<input id="phone" class="form-control" type="text" bind:value={userDetailsChanged.phoneNumber} required />
					</div>
					<div class="form-group">
						<label for="password">Heslo:</label>
						<input id="password" class="form-control" type="password" bind:value={userDetailsChanged.password} />
					</div>
					<div class="form-group">
						<label for="confirmPassword">Potvrzení hesla:</label>
						<input id="confirmPassword" class="form-control" type="password" bind:value={userDetailsChanged.confirmPassword} />
					</div>
					<button type="submit" class="btn btn-success">Uložit</button>
				</form>
			{:else}
				<p><strong>Uživatelské jméno:</strong> {userDetails.username}</p>
				<p><strong>Email:</strong> {userDetails.email}</p>
				<p><strong>Telefon:</strong> {userDetails.phoneNumber}</p>
				<p><strong>Zustatek: </strong>{userDetails.balance}</p>
				<button class="btn btn-primary" on:click={handleEdit}>Upravit</button>
			{/if}
		</div>
		<div class="mt-4">
			<h2>Rezervace</h2>
			<button class="btn btn-success mr-2" on:click={() => goto('/user/account/reservation')}>Vytvořit rezervaci</button>
			<button class="btn btn-info" on:click={() => goto('/user/account/reservations')}>Moje rezervace</button>
		</div>
	{/if}
</div>

<style>
    .container {
        max-width: 800px;
        margin: auto;
    }
    .mt-4 {
        margin-top: 1.5rem;
    }
    .btn {
        margin-top: 1rem;
    }
</style>
