import constants from '../constants';

export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
		}

		connectedCallback () {
			//this.store.subscribe(this.onStateChange);
			this.innerHTML = "<button id =\"btn-res\">Slaps</button>";
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: constants.actions.INCREMENT,
					payload: window.globalGeneratorRate + 1
				});
			});
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
