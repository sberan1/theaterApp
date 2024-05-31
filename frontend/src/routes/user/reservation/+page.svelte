<script>
	// Zatím používáme prázdné pole, v budoucnu se naplní daty z backendu
	import { goto } from '$app/navigation';

	let movies = ["Film 1", "Film 2", "Film 3"];
	let theaters = ["Kino 1", "Kino 2", "Kino 3"];
	let screenings = ["Promítání 1", "Promítání 2", "Promítání 3"];
	let seats = ["Sedadlo 1", "Sedadlo 2", "Sedadlo 3"];

	let selectedMovie = "";
	let selectedTheater = "";
	let selectedScreening = "";
	let selectedSeat = "";
</script>

<button on:click={() => goto('/')}>Vrať se zpět</button>
<h1>Vytvoření rezervace</h1>
<form>
	<label for="movie">Film:</label>
	<select id="movie" bind:value={selectedMovie} required>
		<option value="" disabled selected>Vyberte film</option>
		{#each movies as movie}
			<option value={movie}>{movie}</option>
		{/each}
	</select>

	<label for="theater">Kino:</label>
	<select id="theater" bind:value={selectedTheater} required>
		<option value="" disabled selected>Vyberte kino</option>
		{#each theaters as theater}
			<option value={theater}>{theater}</option>
		{/each}
	</select>

	<label for="screening">Promítání:</label>
	<select id="screening" bind:value={selectedScreening} required>
		<option value="" disabled selected>Vyberte promítání</option>
		{#each screenings as screening}
			<option value={screening}>{screening}</option>
		{/each}
	</select>

	<label for="seat">Sedadlo:</label>
	<select id="seat" bind:value={selectedSeat} required>
		<option value="" disabled selected>Vyberte sedadlo</option>
		{#each seats as seat}
			<option value={seat}>{seat}</option>
		{/each}
	</select>

	<button type="submit">Vytvořit rezervaci</button>
</form>