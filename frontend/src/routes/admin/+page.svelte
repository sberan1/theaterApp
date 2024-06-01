<script>
import { goto } from '$app/navigation';
</script>
<div class="container">
<header>
	<nav>
		<button on:click={() => goto('/admin/projection')}>Přidat projekci</button>
		<button on:click={() => goto('/admin/branch')}>Přidat pobocku</button>
		<button on:click={() => goto('/admin/room')}>Přidat sal</button>
	</nav>
</header>

	<h1>Admin</h1>
</div>
