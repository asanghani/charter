import { UiCharterPage } from './app.po';

describe('ui-charter App', () => {
  let page: UiCharterPage;

  beforeEach(() => {
    page = new UiCharterPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
