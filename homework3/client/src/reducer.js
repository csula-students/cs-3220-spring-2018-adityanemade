import constants from './constants';
import Generator from './models/generator';
import Story from './models/story';

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
			const currentGenerator = action.generatorClicked || 0;
			const gModelCost = new Generator(state.generators[currentGenerator]);
			state.counter -= Math.ceil(gModelCost.getCost());
			state.generators[currentGenerator].quantity += 1;
			const gModel = new Generator(state.generators[currentGenerator]);
			state.generators[currentGenerator].unlockValue = Math.floor(gModel.getCost());
			state.counter = (state.counter < 0) ? 0: state.counter;
			window.globalGeneratorRate += state.generators[currentGenerator].rate;
			return state;
		break;
		case constants.actions.CHECK_STORY:
			state.story.forEach((s, index) => {
				const sModel = new Story(s);
				if(sModel.isUnlockYet(state.counter)){
					sModel.unlock();
				}
				state.story[index] = sModel;
			});
			return state;
		default:
			return state;
	}
}

