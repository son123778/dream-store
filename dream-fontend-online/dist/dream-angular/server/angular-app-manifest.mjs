
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: undefined,
  entryPointToBrowserMapping: {},
  assets: {
    'index.csr.html': {size: 20506, hash: '9acdce192e2a2cf5735a0fdf14932aa292a8dfe77abf8dcefebdf0cb06942e84', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 2246, hash: '5fea931bd9fb61fbb156d5dbd7d71a43cd0f252ae05fa3c4dbf514486cfc8607', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'styles-5T54KCEX.css': {size: 470199, hash: 'Kq3kTdtAWSQ', text: () => import('./assets-chunks/styles-5T54KCEX_css.mjs').then(m => m.default)}
  },
};
