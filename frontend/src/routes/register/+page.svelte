<script>
import { goto } from '$app/navigation';
</script>

<button on:click={() => goto('/')}>Vrať se zpět</button>
<h1>Registrace</h1>
<form>
	<label for="email">Email:</label>
	<input id="email" type="email" required />

	<label for="username">Username:</label>
	<input id="username" required />

	<label for="password">Heslo:</label>
	<input id="password" type="password" required />

	<label for="confirm-password">Potvrdit heslo:</label>
	<input id="confirm-password" type="password" required />

	<button type="submit">Zaregistrovat se</button>
</form>