import {defineConfig} from 'vite'
import path from 'node:path'
import glob from 'fast-glob';
import {normalizePath} from 'vite';

const projectRoot = __dirname
const sourceCodeDir = path.join(projectRoot, 'src/main/resources/static')
const root = sourceCodeDir
const outDir = path.relative(root, path.join(projectRoot, 'target/classes/static'))

const entrypoints = glob
    .sync(`${normalizePath(root)}/**/*`, { onlyFiles: true })
    .filter(i=>i.endsWith('js')||i.endsWith('css'))
    .map((filename) => [path.relative(root, filename), filename])

export default defineConfig({
    root,
    server: {
        watch: {},
        proxy:{
            '/': {
                target: 'http://localhost:8080',
                bypass: function(req, res, proxyOptions) {
                    if (req.url.startsWith('/assets/js/')||req.url.startsWith('/assets/css/')||req.url.startsWith('/@')) {
                        return req.url;
                    }
                    return null;
                }
            }
        },
    },
    appType: "custom",
    build: {
        // ssr: true,
        // outDir에서 manifest.json을 생성합니다.
        manifest: true,
        minify: false,
        outDir,
        assetsDir:"./",
        rollupOptions: {
            input: Object.fromEntries(entrypoints)
        }

    }

})

