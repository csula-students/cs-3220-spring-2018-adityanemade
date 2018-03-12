import constants from '../constants';
import Story from '../models/story';

export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
			newState.story.forEach((s) => {
				if(s.state == "visible"){
					document.getElementById(s.name).style.visibility = s.state;
				}
			});
		}

		connectedCallback () {
			// this.store.subscribe(this.onStateChange);
			this.innerHTML = "<div class = \"game-header\"><header><h1>Stress Reliever</h1></header></div>";
			const storyParent = window.document.createElement('div');
			storyParent.className = "game-story";
			this.store.state.story.forEach((s) => {
				const sModel = new Story(s);
				const storyPara = window.document.createElement('span');
				storyPara.id = s.name;
				storyPara.innerHTML = s.description;
				storyPara.style.visibility = s.state;
				const storyBreak = window.document.createElement('br');
				storyPara.appendChild(storyBreak);
				storyParent.appendChild(storyPara);
			});
			this.append(storyParent);


			this.store.subscribe(this.onStateChange);

		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

