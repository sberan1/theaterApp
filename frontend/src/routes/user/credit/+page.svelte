
<script>
	import axiosInstance from '$lib/axios.instance.js';
	import Cookies from 'js-cookie';
	import { goto } from '$app/navigation';

	let amount = '';

	const addBalance = async () => {
		try {
			let token = Cookies.get('token');
			const parsedToken = parseJwt(token);
			const user = parsedToken.sub;

			const response = await axiosInstance.post(`/user/addBalance/${user}`, { amount });

			if (response.status === 200) {
				alert('Balance added successfully');
				goto('/user/account');
			} else {
				alert('Failed to add balance');
			}
		} catch (error) {
			alert('Failed to add balance');
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
</script>


<div class="container">
	<h1>Add Balance</h1>
	<div class="form-group">
		<label for="amount">Amount:</label>
		<input id="amount" class="form-control" type="number" min="1" bind:value={amount} required />
	</div>
	<button class="btn btn-primary" on:click={addBalance}>Add Balance</button>
</div>
