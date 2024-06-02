<script>
	export let projections = [];
	export let changeSort;
	export let sortBy;
</script>

<table class="table">
	<thead>
	<tr>
		<th on:click={() => changeSort('startTime')}>
			Start Time {sortBy === 'startTime' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('movie.title')}>
			Movie Title {sortBy === 'movie.title' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('priceType')}>
			Price Type {sortBy === 'priceType' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('room.name')}>
			Room Name {sortBy === 'room.name' ?  '↓' : ''}
		</th>
		<th on:click={() => changeSort('room.branch.name')}>
			Branch Name {sortBy === 'room.branch.name' ?  '↓' : ''}
		</th>
	</tr>
	</thead>
	<tbody>
	{#each projections as projection}
		<tr>
			<td>
				{new Date(projection.startTime).toLocaleDateString('cs-CZ', {
					year: 'numeric',
					month: 'long',
					day: 'numeric',
					hour: '2-digit',
					minute: '2-digit'
				})}
			</td>
			<td>{projection.movie ? projection.movie.title : 'N/A'}</td>
			<td>{projection.priceType ? projection.priceType.name : 'N/A'}</td>
			<td>{projection.room ? projection.room.name : 'N/A'}</td>
			<td>{projection.room ? projection.room.branch.name : "N/A"}</td>
		</tr>
	{/each}
	</tbody>
</table>