<script>
	let title = '';
	let durationInMinutes = '';
	let rating = '';
	let genre = '';
	let responseMessage = '';

	const submitForm = async () => {
		const movie = {
			title,
			durationInMinutes: parseInt(durationInMinutes),
			rating,
			genre
		};

		try {
			const response = await fetch('http://localhost:8081/movie', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(movie)
			});

			if (response.ok) {
				responseMessage = 'Movie added successfully!';
			} else {
				responseMessage = 'Failed to add movie.';
			}
		} catch (error) {
			responseMessage = 'Error: ' + error.message;
		}
	};
</script>

<form on:submit|preventDefault={submitForm}>
	<label for="title">Title:</label>
	<input id="title" type="text" bind:value={title} required />

	<label for="durationInMinutes">Duration (in minutes):</label>
	<input id="durationInMinutes" type="number" bind:value={durationInMinutes} required />

	<label for="rating">Rating:</label>
	<input id="rating" type="text" bind:value={rating} required />

	<label for="genre">Genre:</label>
	<input id="genre" type="text" bind:value={genre} required />

	<button type="submit">Submit</button>
	<p>{responseMessage}</p>
</form>