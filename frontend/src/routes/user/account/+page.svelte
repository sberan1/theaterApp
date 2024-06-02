<script>
	import { onMount } from 'svelte';
	import axiosInstance from '$lib/axios.instance.js';
	import { writable } from 'svelte/store';
	import { goto } from '$app/navigation';
	import Cookies from 'js-cookie';

	let userDetails = {
		username: '',
		email: '',
		phone: ''
	};
	let showEditForm = writable(false);
	let loading = writable(false);

	const loadAccountDetails = async () => {
		try {
			loading.set(true);
			const token = Cookies.get('token');
			const response = await axiosInstance.get('/user/account', {
				headers: { 'Authorization': `Bearer ${token}` }
			});
			userDetails = response.data;
			loading.set(false);
		} catch (error) {
			console.error('Error loading account details:', error);
		}
	};

	const handleEdit = () => {
		showEditForm.set(true);
	};

	const handleSave = async () => {
		try {
			const token = Cookies.get('token');
			await axiosInstance.put('/user/account', userDetails, {
				headers: { 'Authorization': `Bearer ${token}` }
			});
			showEditForm.set(false);
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
						<input id="username" class="form-control" type="text" bind:value={userDetails.username} required />
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<input id="email" class="form-control" type="email" bind:value={userDetails.email} required />
					</div>
					<div class="form-group">
						<label for="phone">Telefon:</label>
						<input id="phone" class="form-control" type="text" bind:value={userDetails.phone} required />
					</div>
					<button type="submit" class="btn btn-success">Uložit</button>
				</form>
			{:else}
				<p><strong>Uživatelské jméno:</strong> {userDetails.username}</p>
				<p><strong>Email:</strong> {userDetails.email}</p>
				<p><strong>Telefon:</strong> {userDetails.phone}</p>
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