import constants from './constants';
import Generator from './models/generator';

export default function reducer (state, action) {
	switch (action.type) {
		case constants.actions.EXAMPLE:
			state.example = action.payload;
			return state;
		case constants.actions.INCREMENT:
			state.counter += action.payload;
			return state;
		break;
		case constants.actions.BUY_GENERATOR:
			const currentGenerator = action.payload || 0;

			const gModelCost = new Generator(state.generators[currentGenerator]);
			// state.counter -= state.generators[currentGenerator].baseCost;
			state.counter -= Math.ceil(gModelCost.getCost());
			state.generators[currentGenerator].quantity += 1;


			const gModel = new Generator(state.generators[currentGenerator]);
			// state.generators[currentGenerator].baseCost = Math.ceil(gModel.getCost());
			// console.log(Math.ceil(gModel.getCost()));
			
			state.generators[currentGenerator].unlockValue = Math.ceil(gModel.getCost());

			// console.log("base cost reducer = ",state.generators[currentGenerator].baseCost);
			state.counter = (state.counter < 0) ? 0: state.counter;

			

			// state.generators[currentGenerator].unlockValue = state.generators[currentGenerator].baseCost;


			// console.log(state.generators[currentGenerator].unlockValue);

			

			window.globalGeneratorRate += state.generators[currentGenerator].rate;
			return state;
		break;
		default:
			return state;
	}
}

