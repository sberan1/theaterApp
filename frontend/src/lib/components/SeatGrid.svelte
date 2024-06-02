<script>
	import groupBy from 'group-array';
	export let seats = [];
	export let selectedSeats = [];

	$: groupedSeats = groupBy(seats, 'roomRow');
	//$: selectedSeats = toggleSeatSelection();

	const toggleSeatSelection = (seat) => {
		if (selectedSeats.includes(seat)) {
			selectedSeats = selectedSeats.filter(s => s !== seat);
		} else {
			selectedSeats = [...selectedSeats, seat];
		}
	};
</script>

<div class="seat-grid mt-5">
	{#each Object.keys(groupedSeats) as row}
		<div class="d-flex direction-row">
			<div class="row-label">{row}</div>
			{#each groupedSeats[row] as seat (seat.id)}
				<a
					class="seat {selectedSeats.includes(seat) ? 'selected' : ''}"
					on:click={() => toggleSeatSelection(seat)}
				>
					{seat.seatNumber}
				</a>
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
        cursor: pointer;
    }
    .seat.selected {
        background-color: #4caf50;
    }
    .row-label {
        display: flex;
        flex-direction: row;
        justify-self: end;
        align-self: center;
    }
</style>