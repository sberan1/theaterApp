<script>
	import axiosInstance from '$lib/axios.instance.js';
	import { goto } from '$app/navigation';
	import Cookies from 'js-cookie';
	import { onMount } from 'svelte';

	let username = '';
	let password = '';
	let email = '';
	let phoneNumber = '';
	let confirmPassword = '';

	async function register(user) {
		const response = await axiosInstance.post('http://localhost:8081/register', user);

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		return response.data.accessToken;
	}

	async function handleSubmit() {
		try {
			if (password !== confirmPassword) {
				throw new Error('Passwords do not match');
			}

			const user = { username, password, email, phoneNumber };
			const token = await register(user);
			Cookies.set('token', token, { expires: new Date(new Date().getTime() + 10 * 60 * 60 * 1000) });
		} catch (error) {
			console.error('An error occurred:', error);
		}
	}

	onMount(() => {
		const token = Cookies.get('token');
		if (token) {
			goto('/');
		}
	});
</script>

<button on:click={() => goto('/')}>Vrať se zpět</button>
<div class="container">
<h1>Registrace</h1>
<form on:submit|preventDefault={handleSubmit}>
	<div class="form-group">
	<label for="email">Email:</label>
	<input id="email" class="form-control" bind:value={email} type="email"  />
	</div>

	<div class="form-group">
	<label for="username">Username:</label>
	<input id="username" class="form-control" bind:value={username} required />
		</div>

	<div class="form-group">
	<label for="phone-number">Telefonní číslo:</label>
	<input id="phone-number" class="form-control" type="tel" bind:value={phoneNumber}  />
	</div>

	<div class="form-group">
		<label for="password">Heslo:</label>
		<input id="password" class="form-control" type="password" bind:value={password} required />
	</div>

	<div class="form-group">
		<label for="confirm-password">Potvrdit heslo:</label>
		<input id="confirm-password" class="form-control" type="password" bind:value={confirmPassword} required />
	</div>

	<button type="submit" class="btn btn-primary">Zaregistrovat se</button>
</form>
</div>
