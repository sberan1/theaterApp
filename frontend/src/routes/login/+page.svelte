<script>
	import { goto } from '$app/navigation';
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';

	let username = '';
	let password = '';

	async function login() {
		const response = await axiosInstance.post('http://localhost:8081/login', {
			username,
			password
		});

		if (response.status !== 200) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		 return response.data.accessToken;
	}

	async function handleSubmit() {
		try {
			const token = await login();
			Cookies.set('token',  token, { expires: new Date(new Date().getTime() + 10 * 60 * 60 * 1000) });
			await goto('/');
		} catch (error) {
			console.error('An error occurred:', error);
		}
	}
</script>

<button on:click={() => goto('/')}>Vrať se zpět</button>
<div class="container">
<h1>Přihlášení</h1>
<form on:submit|preventDefault={handleSubmit}>

	<div class="form-group">
	<label for="username">Uživatelské jméno:</label>
	<input id="email" class="form-control" type="text" bind:value={username} required />
		</div>

		<div class="form-group">
	<label for="password">Heslo:</label>
	<input id="password" class="form-control" type="password" bind:value={password} required />
		</div>

	<button type="submit" class="btn-primary btn">Přihlásit se</button>
</form>
</div>
