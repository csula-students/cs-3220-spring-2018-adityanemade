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
			this.innerHTML = "<div class = \"game-header\"><header><h1>Stress Reliever</h1></header></div><div class = \"game-story\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed scelerisque nunc suscipit magna sagittis, ac dictum quam elementum. Sed pharetra placerat libero, eget porta libero consectetur quis. Nulla facilisi. Nulla facilisi. Duis aliquet mauris et imperdiet imperdiet. Vivamus quis nisl a sem elementum rutrum ut gravida dui. Aenean feugiat vel ex a consequat. Fusce vel consectetur justo. Donec vehicula efficitur nunc et auctor. Maecenas at nisi a sem venenatis egestas. Nunc laoreet neque mauris, eget iaculis justo vehicula vitae. Donec bibendum tincidunt sagittis. Fusce a elit at elit dapibus dictum. Aliquam elit velit, aliquet nec justo at, accumsan efficitur mi. Ut et quam eget libero dignissim venenatis id a nulla. Praesent tempus sapien vel orci sodales vehicula. Morbi non ipsum sed ligula pellentesque egestas. Donec at tellus ac mauris iaculis semper. Phasellus sit amet ultricies dui, non vehicula purus. Aenean eu semper enim, vitae faucibus purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer sit amet luctus libero. Donec bibendum tincidunt sagittis. Fusce a elit at elit dapibus dictum. Aliquam elit velit, aliquet nec justo at, accumsan efficitur mi. Ut et quam eget libero dignissim venenatis id a nulla. Praesent tempus sapien vel orci sodales vehicula. Morbi non ipsum sed ligula pellentesque egestas. Donec at tellus ac mauris iaculis semper. Phasellus sit amet ultricies dui, non vehicula purus. Aenean eu semper enim, vitae faucibus purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer sit amet luctus libero. Donec bibendum tincidunt sagittis. Fusce a elit at elit dapibus dictum. Aliquam elit velit, aliquet nec justo at, accumsan efficitur mi. Ut et quam eget libero dignissim venenatis id a nulla. Praesent tempus sapien vel orci sodales vehicula. Morbi non ipsum sed ligula pellentesque egestas. Donec at tellus ac mauris iaculis semper. Phasellus sit amet ultricies dui, non vehicula purus. Aenean eu semper enim, vitae faucibus purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer sit amet luctus libero.</div>";
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

