export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			document.getElementById('resource-counter').innerHTML = this.store.state.counter;
		}

		connectedCallback () {
			this.innerHTML = "<h2>Hits:</h2><h2 id=\"resource-counter\" class=\"resource-counter\">" + this.store.state.counter + "</h2>";
			this.store.subscribe(this.onStateChange);			
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

