<script>
	import { goto } from '$app/navigation';
	import axios from 'axios';
	import Cookies from 'js-cookie';

	let username = '';
	let password = '';

	async function login() {
		const response = await axios.post('http://localhost:8081/login', {
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
			Cookies.set('token',  token, { expires: 1 }); // The cookie will expire in 7 days
			// You can store the token in a cookie or local storage here
		} catch (error) {
			console.error('An error occurred:', error);
		}
	}
</script>

<button on:click={() => goto('/')}>Vrať se zpět</button>
<h1>Přihlášení</h1>
<form on:submit|preventDefault={handleSubmit}>
	<label for="username">username:</label>
	<input id="email" type="text" bind:value={username} required />

	<label for="password">Heslo:</label>
	<input id="password" type="password" bind:value={password} required />

	<button type="submit">Přihlásit se</button>
</form>
