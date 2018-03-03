import constants from '../constants';

export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			/*
			 * Generating action button
			 */
			this.innerHTML = "<button id =\"btn-res\">Slaps</button>";

			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: constants.actions.INCREMENT,
					payload: window.globalGeneratorRate + 1
				});
			});
		}
	};
}
