<script>
	import groupBy from 'group-array';

	export let seats;
	$: groupedSeats = groupBy(seats, 'roomRow');

</script>

<div class="seat-grid mt-5">
	{#each Object.keys(groupedSeats) as row}
		<div class="d-flex direction-row">
			<div class="row-label">{row}</div>
			{#each groupedSeats[row] as seat (seat.id)}
				<div class="seat">{seat.seatNumber}</div>
			{/each}
			<div class="row-label">{row}</div>
			<br>
		</div>
	{/each}
</div>

<style>
    .seat-grid {
        display: flex;
        flex-direction: column;
        align-items: center;
        grid-template-columns: repeat(auto-fill, minmax(20px, 1fr));
        gap: 10px;
    }
    .seat {
        width: 20px;
        height: 20px;
        margin: 5px;
        background-color: #ccc;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 4px;
    }
    .row-label {
        display: flex;
        flex-direction: row;
        justify-self: end;
        align-self: center;
    }
</style>
