// default interval as 1 second
import constants from './constants';

const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */
export function loop (store) {
	// TODO: increment counter based on the generators in the state
	// hint: read how many "generators" in store and iterate through them to
	//       count how many value to increment to "resource"
	
	if (window.globalGeneratorRate > 0) {
		if (window.store.incrementTime)
			clearTimeout(window.store.incrementTime);
		store.dispatch({
					type: constants.actions.INCREMENT,
					payload: window.globalGeneratorRate
				});

	}

	// TODO: triggers stories from story to display state if they are passed
	//       the `triggeredAt` points

	store.dispatch({
		type: constants.actions.CHECK_STORY
	});	

	setTimeout(loop.bind(this, store), interval);
}

export function increment (state, modifier = 1) {
	return state.counter + 1 * modifier;
}
