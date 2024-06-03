<script>
	import { goto } from '$app/navigation';
	import AdminProjectionGrid from '$lib/components/AdminProjectionGrid.svelte';
	import AdminPobockaGrid from '$lib/components/AdminPobockaGrid.svelte';
	import AdminRoomGrid from '$lib/components/AdminRoomGrid.svelte';
	import AdminMovieGrid from '$lib/components/AdminMovieGrid.svelte';
	import AdminPriceGrid from '$lib/components/AdminPriceGrid.svelte';

	let option = "Projekce";
</script>


<header class="p-3 bg-light">
	<nav class="container d-flex flex-wrap justify-content-center">
		<button class="btn btn-outline-primary m-2" on:click={() => goto('/admin/branch')}>Přidat pobocku</button>
		<button class="btn btn-outline-primary m-2" on:click={() => goto('/admin/room')}>Přidat sal</button>
		<button class="btn btn-outline-primary m-2" on:click={() => goto('/admin/movie')}>Přidat film</button>
		<button class="btn btn-outline-primary m-2" on:click={() => goto('/admin/price')}>Přidat cenu</button>
		<button class="btn btn-outline-primary m-2" on:click={() => goto('/admin/projection')}>Přidat projekci</button>
		<button class="btn btn-outline-primary m-2" on:click={() => goto('/admin/seats')}>Přidat sedacky</button>
	</nav>
</header>

<div class="container">
	<h1>Admin</h1>
	<div>
		<button class="btn btn-outline-primary m-2" on:click={() => option = "Pobocka"}>Zobrazit pobocky</button>
		<button class="btn btn-outline-primary m-2" on:click={() => option = "Sal"}>Zobrazit saly</button>
		<button class="btn btn-outline-primary m-2" on:click={() => option = "Film"}>Zobrazit filmy</button>
		<button class="btn btn-outline-primary m-2" on:click={() => option = "Cena"}>Zobrazit ceny</button>
		<button class="btn btn-outline-primary m-2" on:click={() => option = "Projekce"}>Zobrazit projekce</button>
	</div>

	{#if option === "Projekce"}
		<AdminProjectionGrid />
		{:else if option === "Pobocka"}
		<AdminPobockaGrid />
		{:else if option === "Sal"}
		<AdminRoomGrid />
		{:else if option === "Film"}
	<AdminMovieGrid />
	{:else if option === "Cena"}
 		<AdminPriceGrid />
	{/if}

</div>
