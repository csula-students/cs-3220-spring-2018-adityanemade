export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
			
		}

		connectedCallback () {
			// this.store.subscribe(this.onStateChange);
			this.innerHTML = "<div class = \"game-header\"><header><h1>Stress Reliever</h1></header></div><div class = \"game-story\"></div>";
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

